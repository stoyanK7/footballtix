import '../../css/site/UserStatistics.css';

import { useEffect, useRef, useState } from 'react';

import Chart from '../shared/Chart';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import axios from 'axios';
import { faExpand } from '@fortawesome/free-solid-svg-icons';
import useToggle from '../../hooks/useToggle';

const UserStatistics = () => {
  const [data, setData] = useState();
  const [fields, setFields] = useState({
    from: new Date(
      new Date().getFullYear(),
      new Date().getMonth() - 1,
      new Date().getDate()
    ),
    to: new Date()
  });
  const chart = useRef(null);

  const [fullScreen, toggleFullScreen] = useToggle();

  useEffect(() => {
    loadData();
  }, []);

  const onSubmitHandler = e => {
    e.preventDefault();

    loadData();
  };

  const disableFullScreen = () => {
    toggleFullScreen();

    // after transition
    setTimeout(() => {
      chart.current.style.removeProperty('width');
      chart.current.style.removeProperty('height');
      chart.current.style.removeProperty('left');
      chart.current.style.removeProperty('top');
      chart.current.style.removeProperty('position');
    }, 500)
  };

  console.log(chart);
  const enableFullScreen = () => {
    // before transition
    const scrollWidth = chart.current.scrollWidth;
    const scrollHeight = chart.current.scrollHeight;

    chart.current.style.setProperty('position', 'fixed');
    chart.current.style.setProperty('left', 'calc(50vw - 300px)');
    chart.current.style.setProperty('top', chart.current.offsetTop + 'px');
    chart.current.style.setProperty('width', scrollWidth + 'px');
    chart.current.style.setProperty('height', scrollHeight + 'px');

    // wait for code to run
    setTimeout(() => toggleFullScreen(), 100);
  };


  const loadData = () => {
    axios.post('/api/users/stats', {
      from: new Date(fields.from),
      to: new Date(fields.to)
    })
      .then(res => {
        setData(res.data);
      });
  }

  const onChangeHandler = e => {
    setFields({
      ...fields,
      [e.target.name]: e.target.value
    });
  };

  return (
    <div className='user-statistics'>
      <div className='options'>
        <form onSubmit={onSubmitHandler}>
          <span>From</span>
          <input type='date' name='from' defaultValue={fields.from} onChange={onChangeHandler} />
          <span>to</span>
          <input type='date' name='to' defaultValue={fields.to} onChange={onChangeHandler} />
          <button type='submit'>Submit</button>
        </form>
        <div className='full-screen' onClick={enableFullScreen}>
          <FontAwesomeIcon className='nav-icon fa-fw' icon={faExpand} />
          Full screen
        </div>
      </div>
      <div style={{ height: 300 }}>
        {data &&
          <Chart data={data} ref={chart} fullScreen={fullScreen} disableFullScreen={disableFullScreen} />
        }

      </div>
    </div>
  );
}

export default UserStatistics;
