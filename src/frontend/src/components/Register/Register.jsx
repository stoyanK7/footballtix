import React from 'react';
import Logo from '../Logo/Logo.jsx';
import Input from '../Input/Input.jsx';
import { Link } from 'react-router-dom';
import '../css/Link.css';
import '../css/Auth.css';
import '../css/Button.css';

const Register = () => {
  return (
    <div className="Auth">
      <Logo />
      <form className="AuthForm" action="">
        <Input
          name="e-mail"
          placeholder="E-mail"
          type="email" />
        <Input
          name="full-name"
          placeholder="Full name"
          type="text" />
        <Input
          name="password"
          placeholder="Password"
          type="password" />
        <p className="Text">
          Already have an account?
          <br />
          <Link className="Link" to="/login">Log in.</Link>
        </p>
        <button className="Button" type="submit">Register</button>
      </form>
    </div>
  );
};

export default Register;
