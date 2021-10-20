import React, { useState } from 'react';
import { faBars } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import FootballTix from '../FootballTix/FootballTix.jsx';
import Navbar from '../Navbar/Navbar';
import { Link } from 'react-router-dom';
import './Header.css';

const Header = () => {
  const [navbarStyle, setNavbarStyle] = useState({ display: "none" });

  const toggleNavbar = () => {
    if (navbarStyle.display === "none") {
      setNavbarStyle({ display: "grid" });
    } else {
      setNavbarStyle({ display: "none" });
    }
  };
  
  return (
    <header className="Header">
      <FontAwesomeIcon icon={faBars} className="Bars" onClick={toggleNavbar} />
      <Link to="/" className="FootballTixLink">
        <FootballTix />
      </Link>
      <Navbar style={navbarStyle} />
    </header>
  );
};

export default Header;
