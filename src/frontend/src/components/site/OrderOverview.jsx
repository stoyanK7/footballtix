import '../../css/site/OrderOverview.css';

import Loading from '../shared/Loading';
import MatchInfo from '../shared/MatchInfo';
import MatchPrice from '../shared/MatchPrice';
import MessageBox from '../shared/MessageBox';
import axios from 'axios';
import useFetch from '../../hooks/useFetch';
import { useParams } from 'react-router';
import { useState } from 'react';

const OrderOverview = () => {
  const { orderId } = useParams();
  const { fetchData: order, isFetching, fetchError } = useFetch(`/api/orders/${orderId}`);
  const [email, setEmail] = useState('');
  const [response, setResponse] = useState();
  const [responseError, setResponseError] = useState();
  const onChangeHandler = e => setEmail(e.target.value);
  const onSubmitHandler = e => {
    e.preventDefault();
    axios.post('/api/tickets/send', { email: email, order: order })
      .then(res => setResponse('Ticket sent. Check your email.'))
      .catch(err => setResponseError('Something went wrong. Please try again later.'));
  };

  return (
    <>
      {isFetching && <Loading />}
      {fetchError && <MessageBox content={fetchError} type='error' />}
      {response && <MessageBox content={response} setContent={setResponse} type='success' />}
      {responseError && <MessageBox content={responseError} setContent={setResponseError} type='error' />}
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
            <p><b>Transaction time: </b>{order.transactionDateTime}</p>
            <p><b>Transaction ID: </b>{order.transactionId}</p>
          </div>
          <span><b>Request ticket to email</b></span>
          <div className="request-ticket">
            <form onSubmit={onSubmitHandler}>
              <input type="email" placeholder='Email' name='email' required onChange={onChangeHandler} />
              <button><b>Request</b></button>
            </form>
          </div>
        </div>
      }
    </>
  );
};

export default OrderOverview;
