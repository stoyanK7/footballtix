import '../css/components/Footer.css';

import FootballTix from './FootballTix';
import { Link } from 'react-router-dom';

const Footer = () => {
  return (
    <footer>
      <FootballTix />
      <div className='footer-links'>
        <Link to='/contact'>Contact us</Link>
        <Link to='/privacy'>Privacy</Link>
        <Link to='/terms'>Terms and conditions</Link>
      </div>
      <div className='all-rights-reserved'>&copy; footballtix<br />All rights reserved.</div>
    </footer>
  );
};

export default Footer;
