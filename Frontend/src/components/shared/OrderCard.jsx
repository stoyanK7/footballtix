import '../../css/shared/OrderCard.css';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faQrcode } from '@fortawesome/free-solid-svg-icons';
import formatDate from '../../util/formatDate';

const OrderCard = ({ fullName, footballMatch }) => {
  return (
    <div className="order-card">
      <FontAwesomeIcon className='fa fa-fw' icon={faQrcode} />
      <div>
        <span>{fullName}</span>
        <span><b>{footballMatch.homeTeam}</b> vs <b>{footballMatch.awayTeam}</b></span>
        <span>{formatDate(footballMatch.startingDateTime)}</span>
      </div>
    </div>
  );
};

export default OrderCard;
