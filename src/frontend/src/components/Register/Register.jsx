import { useState } from 'react';
import { Redirect, Link } from 'react-router-dom';
import axios from 'axios';
import Logo from '../Logo/Logo.jsx';
import Input from '../Input/Input.jsx';
import '../css/Link.css';
import '../css/Form.css';
import '../css/Button.css';
import '../css/FormTitle.css';

const Register = () => {
  const [fields, setFields] = useState({
    "e-mail": "",
    "full-name": "",
    "password": ""
  });
  const [redirect, setRedirect] = useState(false);

  const onChange = (e) => {
    setFields({
      ...fields,
      [e.target.name]: e.target.value
    });
  };

  const register = async (e) => {
    e.preventDefault();
    await axios.post("/api/register", {
      email: fields['e-mail'],
      fullName: fields['full-name'],
      password: fields.password
    })
      .then((res) => {
        console.log(res);
        setRedirect(true);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  if (redirect) {
    return <Redirect to="/login" />;
  }

  return (
    <div className="FormWrapper">
      <Logo />
      <h1 className="FormTitle">Register</h1>
      <form className="Form" onSubmit={register}>
        <Input
          name="e-mail"
          placeholder="E-mail"
          type="email"
          onChange={onChange} />
        <Input
          name="full-name"
          placeholder="Full name"
          type="text"
          onChange={onChange} />
        <Input
          name="password"
          placeholder="Password"
          type="password"
          onChange={onChange} />
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
