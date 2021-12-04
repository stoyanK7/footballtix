import '../../css/shared/MatchCard.css';

import pad from '../../util/pad';

const MatchCard = ({ homeTeam, awayTeam, startingDateTime, stadium, location, league }) => {
  startingDateTime = new Date(startingDateTime);

  return (
    <div className='match-card'>
      <div className='date'>
        <span className='month'>{startingDateTime.toLocaleString('default', { month: 'short' })}</span>
        <span className='day'>{pad(startingDateTime.getDate(), 2)}</span>
        <span className='year'>{startingDateTime.getFullYear()}</span>
      </div>
      <div className='match'>
        <span className='league'>{league}</span>
        <div>
          <span className='rival'>{homeTeam}</span>
          <span>&nbsp;v&nbsp;</span>
          <span className='rival'>{awayTeam}</span>
        </div>
        <div>
          <span className='time'>{pad(startingDateTime.getHours(), 2)}:{pad(startingDateTime.getMinutes(), 2)}</span>
          <span>&nbsp;-&nbsp;</span>
          <span>{stadium}, {location}</span>
        </div>
      </div>
    </div>
  );
};

export default MatchCard;
