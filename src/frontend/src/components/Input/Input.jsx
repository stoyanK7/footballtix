import './Input.css';

const Input = ({ type, name, placeholder, onChange }) => {
  return (
    <input
      className="Input"
      type={type}
      name={name}
      placeholder={placeholder}
      required
      onChange={onChange} />
  );
};

export default Input;