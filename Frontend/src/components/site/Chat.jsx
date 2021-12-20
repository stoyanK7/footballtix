import '../../css/site/Chat.css';

import { useEffect } from 'react';
import useScript from '../../hooks/useScript';
import useToken from '../../hooks/useToken';

const Chat = () => {
  const { email } = useToken();
  useScript('https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js');
  useScript('https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js');
  useScript('/js/chat.js');

  // Before component unmounts
  useEffect(() => {
    return () => {
      window.disconnect();
    }
  }, []);

  return (
    <>
      <div id="username-page" className="hidden">
        <div className="username-page-container">
          <h1 className="title">Type your username</h1>
          <form id="usernameForm" name="usernameForm">
            <div className="form-group">
              <input type="text" id="name" placeholder="Username"
                className="form-control" value={email()} />
            </div>
            <div className="form-group">
              <button type="submit" className="accent username-submit">Start
                Chatting</button>
            </div>
          </form>
        </div>
      </div>

      <div id="chat-page" className="hidden">
        <div className="chat-container">
          <div className="chat-header">
            <h2>footballTix Discussion</h2>
          </div>
          <div className="connecting">Connecting...</div>
          <ul id="messageArea">

          </ul>
          <form id="messageForm" name="messageForm" nameForm="messageForm">
            <div className="form-group">
              <div className="input-group clearfix">
                <input type="text" id="message" placeholder="Type a message..."
                  autoComplete="off" className="form-control" />
                <button type="submit" className="primary">Send</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </>
  );
};

export default Chat;
