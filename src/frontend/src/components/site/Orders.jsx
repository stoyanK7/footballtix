import '../../css/site/Orders.css';

import Loading from '../shared/Loading';
import MessageBox from '../shared/MessageBox';
import OrderList from '../shared/OrderList';
import useGET from '../../hooks/useGET';
import useToken from '../../hooks/useToken';

const Orders = () => {
  const { email } = useToken();

  const { data: orders, isPending, error } = useGET('/api/orders', { params: { email: email() } });

  return (
    <>
      {isPending && <Loading />}
      {error && <MessageBox content={error} type='error' />}
      {orders &&
        <div className="orders">
          <OrderList orders={orders} />
        </div>
      }
    </>
  );
}

export default Orders;
