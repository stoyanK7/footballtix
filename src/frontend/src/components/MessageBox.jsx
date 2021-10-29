import '../css/components/MessageBox.css';

import { useEffect, useState } from 'react';

const MessageBox = ({ content }) => {
  const [display, setDisplay] = useState("0");
    
  // no idea how this worked, dont touch it
  useEffect(() => {
    setDisplay("1");
    setInterval(() => {
      setDisplay("0");
    }, 5000);
  }, []);

  return (
    <div className="MessageBox" style={{ opacity: display }}>
      {content}
    </div>
  )
};

export default MessageBox;