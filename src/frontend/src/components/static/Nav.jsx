import '../../css/static/Nav.css';

import { faChartLine, faPlusCircle, faSignInAlt, faSignOutAlt, faTicketAlt, faUserCog, faUserPlus } from '@fortawesome/free-solid-svg-icons';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Link } from 'react-router-dom';
import useToken from '../../hooks/useToken';

const Nav = ({ style, toggleNavbar }) => {
  const { token, isAdmin } = useToken();

  return (
    <nav style={style}>
      {!token &&
        <>
          <Link to='/login' onClick={toggleNavbar}>
            <FontAwesomeIcon className='nav-icon fa-fw' icon={faSignInAlt} />
            <span className='nav-text'>Log in</span>
          </Link>
          <Link to='/register' onClick={toggleNavbar}>
            <FontAwesomeIcon className='nav-icon fa-fw' icon={faUserPlus} />
            <span className='nav-text'>Register</span>
          </Link>
        </>
      }
      {token &&
        <>
          <Link to='/profile' onClick={toggleNavbar}>
            <FontAwesomeIcon className='nav-icon fa-fw' icon={faUserCog} />
            <span className='nav-text'>Profile</span>
          </Link>
          <Link to='/orders' onClick={toggleNavbar}>
            <FontAwesomeIcon className='nav-icon fa-fw' icon={faTicketAlt} />
            <span className='nav-text'>Orders</span>
          </Link>
          {isAdmin() &&
            <Link to='/matches/create' onClick={toggleNavbar}>
              <FontAwesomeIcon className='nav-icon fa-fw' icon={faPlusCircle} />
              <span className='nav-text'>Create match</span>
            </Link>
          }
          {isAdmin() &&
            <Link to='/stats' onClick={toggleNavbar}>
            <FontAwesomeIcon className='nav-icon fa-fw' icon={faChartLine} />
            <span className='nav-text'>User statistics</span>
          </Link>
          }
          <Link to='/logout' onClick={toggleNavbar}>
            <FontAwesomeIcon className='nav-icon fa-fw' icon={faSignOutAlt} />
            <span className='nav-text'>Log out</span>
          </Link>
        </>
      }
    </nav>
  );
};

export default Nav;
