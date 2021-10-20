import '../css/Image.css';

const Image = ({ src, alt, ...props }) => {
  return (
    <img className="Logo" src={src} alt={alt} {...props} />
  );
};

export default Image;
