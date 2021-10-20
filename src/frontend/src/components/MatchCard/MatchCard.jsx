import './MatchCard.css';

const MatchCard = ({id, homeTeam, awayTeam, startingDateTime, stadium, location, league}) => {
  


  return (
    <div key={id} className="MatchCard">
      <div className="MatchCardDate MatchCardPart">
        <span className="MatchCardDateMonth">{new Date(startingDateTime).toLocaleString('default', { month: 'short' })}</span>
        <span className="MatchCardDateDay">{new Date(startingDateTime).getDate().toLocaleString('en-US', {minimumIntegerDigits: 2, useGrouping:false})}</span>
        <span className="MatchCardDateYear">{new Date(startingDateTime).getFullYear()}</span>
      </div>
      <div className="MatchCardMatch MatchCardPart">
        <span className="MatchCardMatchLeague">{league}</span>
        <div>
          <span className="MatchCardMatchRivals">{homeTeam}</span>
          <span>&nbsp;v&nbsp;</span>
          <span className="MatchCardMatchRivals">{awayTeam}</span>
        </div>
        <div>
          <span className="MatchCardMatchDate">{new Date(startingDateTime).getHours().toLocaleString('en-US', {minimumIntegerDigits: 2, useGrouping:false})}:{new Date(startingDateTime).getMinutes().toLocaleString('en-US', {minimumIntegerDigits: 2, useGrouping:false})}</span>
          <span>&nbsp;-&nbsp;</span>
          <span className="MatchCardMatchPlace">{stadium}, {location}</span>
        </div>
      </div>
    </div>
  );
};

export default MatchCard;
