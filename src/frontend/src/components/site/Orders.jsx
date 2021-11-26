import '../../css/site/Orders.css';

import Loading from '../shared/Loading';
import MessageBox from '../shared/MessageBox';
import OrderList from '../shared/OrderList';
import useFetch from '../../hooks/useFetch';
import useToken from '../../hooks/useToken';
import { useState } from 'react';
import { faArrowDown, faArrowUp } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

const Orders = () => {
  const { email } = useToken();
  const { fetchData: orders, isFetching, fetchError} = useFetch('/api/orders', { params: { email: email() } });
  const [showMore, setShowMore] = useState(5);

  const total = (orders) => {
    let total = 0;
    orders.forEach(order => {
      total += order.footballMatch.pricePerTicket;
    });
    return total;
  };

  return (
    <>
      {isFetching && <Loading />}
      {fetchError && <MessageBox content={fetchError} type='error' />}
      {orders &&
        <div className='orders'>
          <h2 className='total'>Total spent: €{total(orders)}</h2>
          <OrderList orders={orders.slice(0, showMore)} />
          {showMore >= orders.length ?
            <button className='show-more' onClick={() => setShowMore(5)}>Collapse <FontAwesomeIcon className='nav-icon fa-fw' icon={faArrowUp} /></button>
            :
            <button className='show-more' onClick={() => setShowMore(showMore + 5)}>Show more <FontAwesomeIcon className='nav-icon fa-fw' icon={faArrowDown} /></button>
          }
        </div>
      }
    </>
  );
};

export default Orders;
