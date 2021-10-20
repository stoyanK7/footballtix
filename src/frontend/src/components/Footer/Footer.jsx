import { Link } from 'react-router-dom';
import FootballTix from '../FootballTix/FootballTix.jsx';
import './Footer.css';

const Footer = () => {
  return (
    <footer className="Footer">
      <FootballTix />
      <div className="Links">
        <Link className="Link" to="/about">About us</Link>
        <Link className="Link" to="/contact">Contact us</Link>
        <Link className="Link" to="/privacy">Privacy</Link>
      </div>
      <div className="AllRightsReserved">
        &copy; footballtix
        <br />
        All rights reserved.
      </div>
    </footer>
  );
};

export default Footer;
