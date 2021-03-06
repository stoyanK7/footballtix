import '../../css/static/NotFound.css';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faRobot } from '@fortawesome/free-solid-svg-icons';

const NotFound = () => {
  return (
    <div className='not-found'>
      <FontAwesomeIcon icon={faRobot} className='icon' />
      <div title='404'>
        404
      </div>
      <p>not found..</p>
    </div>
  );
};

export default NotFound;
