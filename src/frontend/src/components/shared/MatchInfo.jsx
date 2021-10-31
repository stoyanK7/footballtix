import { useState, useEffect } from 'react';
import '../../css/shared/MatchInfo.css';
import pad from '../../util/pad';

const MatchInfo = ({ league, homeTeam, awayTeam, stadium, location, startingDateTime }) => {
  const [formattedDate, setFormattedDate] = useState();

  const formatDate = (date) => {
    if (!(date instanceof Date)) date = new Date(date);
    const weekDay = date.toLocaleDateString('en-US', { weekday: 'long' });
    const day = date.getDay();
    const month = date.toLocaleString('en-US', { month: 'long' });
    const year = date.getFullYear();
    const hours = pad(date.getHours(), 2)
    const minutes = pad(date.getMinutes(), 2);
    return `${weekDay}, ${day} ${month} ${year} ${hours}:${minutes}`;
  };

  useEffect(() => setFormattedDate(formatDate(startingDateTime)), []);

  return (
    <div className="match-info">
      <span className='league'>{league}</span>
      <div>
        <span className='rival'>{homeTeam}</span>
        &nbsp;v&nbsp;
        <span className='rival'>{awayTeam}</span>
      </div>
      <span className='location'>{stadium}, {location}</span>
      <span className='date'>{formattedDate}</span>
    </div>
  );
}

export default MatchInfo;
