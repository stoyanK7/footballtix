import '../../css/site/OrderOverview.css';

import Loading from '../shared/Loading';
import MatchInfo from '../shared/MatchInfo';
import MatchPrice from '../shared/MatchPrice';
import MessageBox from '../shared/MessageBox';
import axios from 'axios';
import formatDate from '../../util/formatDate';
import useGET from '../../hooks/useGET';
import { useParams } from 'react-router';
import { useState } from 'react';

const OrderOverview = () => {
  const { orderId } = useParams();

  const { data: order, isPending, error } = useGET(`/api/orders/${orderId}`);

  const [email, setEmail] = useState('');
  const onChangeHandler = e => setEmail(e.target.value);
  const onSubmitHandler = e => {
    e.preventDefault();
    axios.post('/api/tickets/send', {email: email, order: order})
      .then(res => {
        console.log(res);
      })
      .catch(err => {

      });
  };

  return (
    <>
      {isPending && <Loading />}
      {error && <MessageBox content={error} type='error' />}
      {order &&
        <div className='order-overview'>
          <img src='/img/stadium.png' alt='' />
          <MatchInfo {...order.footballMatch} />
          <span><b>Price</b></span>
          <MatchPrice pricePerTicket={order.footballMatch.pricePerTicket} />
          <span><b>Order details</b></span>
          <div className="order-details">
            <p><b>Order ID: </b>{order.id}</p>
            <p><b>Name: </b>{order.fullName}</p>
            <p><b>Email: </b>{order.email}</p>
            <p><b>Phone number: </b>{order.mobilePhone}</p>
            <p><b>Address: </b>{order.address}</p>
            <p><b>Town/City: </b>{order.city}</p>
            <p><b>Country: </b>{order.country}</p>
            <p><b>Postcode: </b>{order.postcode}</p>
            <p><b>Transaction time: </b>{order.transactionTime}</p>
            <p><b>Transaction ID: </b>{order.transactionId}</p>
            <p><b>Match: </b>{order.footballMatch.homeTeam} vs {order.footballMatch.awayTeam}</p>
            <p><b>Match date: </b>{formatDate(order.footballMatch.startingDateTime)}</p>
          </div>
          <span><b>Request ticket to email</b></span>
          <div className="request-ticket">
            <form onSubmit={onSubmitHandler}>
              <input type="email" placeholder='Email' name='email' required  onChange={onChangeHandler}/>
              <button><b>Request</b></button>
            </form>

          </div>
        </div>
      }
    </>
  );
};

export default OrderOverview;
