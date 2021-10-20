import { useState } from 'react';
import { Link, Redirect } from 'react-router-dom';
import axios from 'axios';
import Logo from '../Logo/Logo.jsx';
import Input from '../Input/Input.jsx';
import '../css/Link.css';
import '../css/Form.css';
import '../css/Button.css';
import '../css/FormTitle.css';

const Login = () => {
  const [fields, setFields] = useState({
    "e-mail": "",
    "password": ""
  });
  const [redirect, setRedirect] = useState(false);

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
        const jwt = res.data.jwt;
        sessionStorage.setItem('jwtToken', jwt);
        setRedirect(true);

      })
      .catch((err) => {
        console.log(err);
      });
  };

  if(redirect) {
    return <Redirect to="/" />;
  }

  return (
    <div className="FormWrapper">
      <Logo />
      <h1 className="FormTitle">Log in</h1>
      <form className="Form" onSubmit={authenticate}>
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
