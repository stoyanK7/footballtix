import { useState } from 'react'
import Logo from '../Logo/Logo.jsx';
import Input from '../Input/Input.jsx';
import { Link } from 'react-router-dom';
import '../css/Link.css';
import '../css/Auth.css';
import '../css/Button.css';
import axios from 'axios';

const Login = () => {
  const [fields, setFields] = useState({
    "e-mail": "",
    "password": ""
  });

  const onChange = (e) => {
    setFields({
      ...fields,
      [e.target.name]: e.target.value
    });
  };

  const authenticate = async (e) => {
    e.preventDefault();
    await axios.post("/api/authenticate", {
      email: fields['e-mail'],
      password: fields.password
    })
      .then((res) => {
        console.log(res.data.jwt);

      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <div className="Auth">
      <Logo />
      <form className="AuthForm" action="" onSubmit={authenticate}>
        <Input
          name="e-mail"
          placeholder="E-mail"
          type="email"
          onChange={onChange} />
        <Input
          name="password"
          placeholder="Password"
          type="password"
          onChange={onChange} />
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
