import React from 'react';
import { faSignInAlt, faSignOutAlt, faTicketAlt, faUserCog, faUserPlus } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Link } from 'react-router-dom';
import './Navbar.css';
import Logout from '../Logout/Logout.jsx';
import useToken from '../../hooks/useToken.jsx';

const Navbar = ({ style }) => {
  const { token } = useToken();

  return (
    <nav className="Navbar" style={style}>
      {!token &&
        <Link className="Link" to="/login">
          <FontAwesomeIcon className="NavbarIcon" icon={faSignInAlt} />
          <span className="NavbarText">Log in</span>
        </Link>}
      {!token &&
        <Link className="Link" to="/register">
          <FontAwesomeIcon className="NavbarIcon" icon={faUserPlus} />
          <span className="NavbarText">Register</span>
        </Link>}
      {token &&
        <Link className="Link" to="/profile">
          <FontAwesomeIcon className="NavbarIcon" icon={faUserCog} />
          <span className="NavbarText">Profile</span>
        </Link>
      }
      {token &&
        <Link className="Link" to="/tickets">
          <FontAwesomeIcon className="NavbarIcon" icon={faTicketAlt} />
          <span className="NavbarText">Tickets</span>
        </Link>
      }
      {token &&
        <Link className="Link" to="/login" onClick={Logout}>
          <FontAwesomeIcon className="NavbarIcon" icon={faSignOutAlt} />
          <span className="NavbarText">Log out</span>
        </Link>
      }

    </nav>
  );
};

export default Navbar;
