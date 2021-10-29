import './Input.css';

const Input = ({ type, name, placeholder, onChange, ...props }) => {
  return (
    <input
      className="Input"
      type={type}
      name={name}
      placeholder={placeholder}
      required
      onChange={onChange} 
      {...props}/>
  );
};

export default Input;