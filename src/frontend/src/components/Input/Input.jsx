import React from 'react';
import './Input.css';

const Input = ({ type, name, placeholder }) => {
  return (
    <input
      className="Input"
      type={type}
      name={name}
      placeholder={placeholder}
      required />
  );
};

export default Input;