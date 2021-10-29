import '../css/components/Profile.css';

import { useEffect, useState } from 'react';

import Logout from './Logout';
import { Redirect } from 'react-router';
import axios from 'axios';
import jwt from 'jwt-decode';
import useToken from '../hooks/useToken';

const Profile = () => {
  const [informationFields, setInformationFields] = useState({
    "newEmail": "",
    "newFullName": ""
  });

  const onChangeInformationFields = (e) => {
    setInformationFields({
      ...informationFields,
      [e.target.name]: e.target.value
    });
  };

  const [passwordFields, setPasswordFields] = useState({
    "currentPassword": "",
    "newPassword": "",
    "confirmPassword": ""
  });

  const onChangePasswordFields = (e) => {
    setPasswordFields({
      ...passwordFields,
      [e.target.name]: e.target.value
    });
  };

  const { token } = useToken();

  // Get user info on load
  useEffect(() => {
    axios.get("/api/users/info", {
      params: {
        jwt: token
      }
    })
      .then((res) => {
        setInformationFields({
          "newEmail": res.data.email,
          "newFullName": res.data.fullName
        });
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  const [redirect, setRedirect] = useState(false);
  const updateInfo = async (e) => {
    e.preventDefault();
    await axios.put("/api/users/info", {
      oldEmail: jwt(token).sub,
      ...informationFields
    })
      .then((res) => {
        Logout();
        setRedirect(true);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const updatePassword = async (e) => {
    e.preventDefault();
    await axios.put("/api/users/password", {
      jwt: token,
      ...passwordFields
    })
      .then((res) => {
        Logout();
        setRedirect(true);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  if (redirect) {
    return (<Redirect to="/login" />);
  }

  return (
    <div className="Profile">
      <img src="/img/user-settings.png" />
      <div>
        <h2>Edit information</h2>
        <form onSubmit={updateInfo}>
          <label htmlFor="email">Email:</label>
          <input
            id="email"
            name="newEmail"
            placeholder="E-mail"
            type="email"
            value={informationFields.newEmail}
            onChange={onChangeInformationFields} />
          <label htmlFor="fullName">Full name:</label>
          <input
            id="fullName"
            name="newFullName"
            placeholder="Full name"
            type="text"
            value={informationFields.newFullName}
            onChange={onChangeInformationFields} />
          <button type="submit">Save info</button>
        </form>
      </div>
      <div>
        <h2>Edit password</h2>
        <form onSubmit={updatePassword}>
          <input
            name="currentPassword"
            placeholder="Current password"
            type="password"
            onChange={onChangePasswordFields} />
          <input
            name="newPassword"
            placeholder="New password"
            type="password"
            onChange={onChangePasswordFields} />
          <input
            name="confirmPassword"
            placeholder="Confirm password"
            type="password"
            onChange={onChangePasswordFields} />
          <button type="submit">Save password</button>
        </form>
      </div>
    </div>
  )
};

export default Profile;
