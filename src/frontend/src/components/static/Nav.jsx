import '../../css/static/Nav.css';

import { faSignInAlt, faSignOutAlt, faTicketAlt, faUserCog, faUserPlus } from '@fortawesome/free-solid-svg-icons';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Link } from 'react-router-dom';
import Logout from '../account/Logout';
import useToken from '../../hooks/useToken';

const Nav = ({ style }) => {
  const { token } = useToken();

  return (
    <nav style={style}>
      {!token &&
        <>
          <Link to='/login'>
            <FontAwesomeIcon className='nav-icon fa-fw' icon={faSignInAlt} />
            <span className='nav-text'>Log in</span>
          </Link>
          <Link to='/register'>
            <FontAwesomeIcon className='nav-icon fa-fw' icon={faUserPlus} />
            <span className='nav-text'>Register</span>
          </Link>
        </>
      }
      {token &&
        <>
          <Link to='/profile'>
            <FontAwesomeIcon className='nav-icon' icon={faUserCog} />
            <span className='nav-text'>Profile</span>
          </Link>
          <Link to='/tickets'>
            <FontAwesomeIcon className='nav-icon' icon={faTicketAlt} />
            <span className='nav-text'>Tickets</span>
          </Link>
          <Link to='/login' onClick={Logout}>
            <FontAwesomeIcon className='nav-icon' icon={faSignOutAlt} />
            <span className='nav-text'>Log out</span>
          </Link>
        </>
      }
    </nav>
  );
};

export default Nav;
