import Logo from '../Logo/Logo.jsx';
import Input from '../Input/Input.jsx';
import { Link } from 'react-router-dom';
import '../css/Link.css';
import '../css/Auth.css';
import '../css/Button.css';

const Login = () => {
  return (
    <div className="Auth">
      <Logo />
      <form className="AuthForm" action="">
        <Input
          name="e-mail"
          placeholder="E-mail"
          type="email" />
        <Input
          name="password"
          placeholder="Password"
          type="password" />
        <p className="Text">
          Don't have an account?
          <br />
          <Link className="Link" to="/register">Register.</Link>
        </p>
        <button className="Button" type="submit">Log in</button>
      </form>
    </div>
  );
};

export default Login;
