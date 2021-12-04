import '../../css/static/Contact.css';

import MessageBox from '../shared/MessageBox';
import { useState } from 'react';

const Contact = () => {
  const [message, setMessage] = useState();

  const onClickHandler = e => {
    const text = e.currentTarget.childNodes[0].childNodes[1].innerText;
    navigator.clipboard.writeText(text);
    setMessage(`Copied to clipboard: ${text}`);
  };

  return (
    <>
      {message && <MessageBox content={message} type='success' />}
      <div className='contact'>
        <div onClick={onClickHandler}>
          <p>
            Call us at <span >+359-878-890-852</span>
          </p>
          <img src='/img/phone-call.webp' alt='Phone' />
        </div>
        <div onClick={onClickHandler}>
          <p>
            Email us at <span >office@footballtix.com</span>
          </p>
          <img src='/img/email.webp' alt='Mail' />
        </div>
      </div>
    </>
  )
};

export default Contact;
