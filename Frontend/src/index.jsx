import './css/global/a.css';
import './css/global/button.css';
import './css/global/form.css';
import './css/global/img.css';
import './css/global/index.css';
import './css/global/input.css';
import './css/global/form-wrapper.css';

import App from './components/site/App';
import { BrowserRouter } from 'react-router-dom';
import React from 'react';
import ReactDOM from 'react-dom';

ReactDOM.render(
    <BrowserRouter>
      <App />
    </BrowserRouter>,
  document.getElementById('root')
);
