import { Link } from 'react-router-dom';
import OrderCard from '../shared/OrderCard';

const OrderList = ({ orders }) => {
  return (
    <div>
      {orders.map(order => (
        <Link to={`/orders/${order.id}`} key={order.id}>
          <OrderCard {...order} />
        </Link>
      ))}
    </div>
  );
};

export default OrderList;
