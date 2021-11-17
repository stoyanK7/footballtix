import '../../css/shared/MatchInfo.css';

import formatDate from '../../util/formatDate';

const MatchInfo = ({ league, homeTeam, awayTeam, stadium, location, startingDateTime }) => {
  return (
    <div className="match-info">
      <span className='league'>{league}</span>
      <div>
        <span className='rival'>{homeTeam}</span>
        &nbsp;v&nbsp;
        <span className='rival'>{awayTeam}</span>
      </div>
      <span className='location'>{stadium}, {location}</span>
      <span className='date'>{formatDate(startingDateTime)}</span>
    </div>
  );
};

export default MatchInfo;
