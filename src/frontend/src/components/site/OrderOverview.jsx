import '../../css/site/OrderOverview.css';

import Loading from '../shared/Loading';
import MatchInfo from '../shared/MatchInfo';
import MatchPrice from '../shared/MatchPrice';
import MessageBox from '../shared/MessageBox';
import useGET from '../../hooks/useGET';
import { useParams } from 'react-router';

const OrderOverview = () => {
  const { orderId } = useParams();

  const { data: order, isPending, error } = useGET(`/api/orders/${orderId}`);
  return (
    <>
      {isPending && <Loading />}
      {error && <MessageBox content={error} type='error' />}
      {order &&
        <div className='order-overview'>
          <img src='/img/stadium.png' alt=''/>
          <MatchInfo {...order.footballMatch}/>
          <span><b>Price</b></span>
          <MatchPrice pricePerTicket={order.footballMatch.pricePerTicket} />
          <span><b>Order details</b></span>
        </div>
      }
    </>
  );
};

export default OrderOverview;
