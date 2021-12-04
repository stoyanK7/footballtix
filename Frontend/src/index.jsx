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
import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8080/';

ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </React.StrictMode>,
  document.getElementById('root')
);
