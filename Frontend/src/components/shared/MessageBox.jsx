import '../../css/shared/MessageBox.css';

import { useEffect, useState } from 'react';

import calcReadTime from '../../util/calcReadTime';

const hidden = { opacity: '0' };
const visible = { opacity: '1' };

const MessageBox = ({ content, type, setContent }) => {
  const [style, setStyle] = useState(hidden);

  // no idea how this worked, dont touch it
  useEffect(() => {
    setStyle(visible);
    const timeout = calcReadTime(content);
    setTimeout(() => {
      setStyle(hidden);
      if (setContent) setContent();
    }, timeout);
  }, [content]);

  const onClickHandler = () => setStyle(hidden);

  return (
    <div className={`message-box no-select ${type}`} style={style} onClick={onClickHandler}>
      {content}
    </div>
  );
};

export default MessageBox;
