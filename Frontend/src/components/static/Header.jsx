import '../../css/static/Header.css';

import React, { useState } from 'react';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import FootballTix from './FootballTix';
import { Link } from 'react-router-dom';
import Navbar from './Nav';
import { faBars } from '@fortawesome/free-solid-svg-icons';

const Header = () => {
  const [navbarStyle, setNavbarStyle] = useState({ height: '0' });

  const toggleNavbar = () => {
    if (navbarStyle.height === '0') setNavbarStyle({ height: document.getElementsByTagName('nav')[0].scrollHeight });
    else setNavbarStyle({ height: '0' });
  };

  return (
    <header>
      <FontAwesomeIcon icon={faBars} className='burger' onClick={toggleNavbar} />
      <Link to='/' className='footballTix-link'><FootballTix /></Link>
      <Navbar style={navbarStyle} toggleNavbar={toggleNavbar}/>
    </header>
  );
};

export default Header;
