import { Link } from 'react-router-dom';
import MatchCard from '../shared/MatchCard';

const MatchList = ({ matches }) => {
  return (
    <div>
      {matches.map(match => (
        <Link to={`/matches/${match.id}`} key={match.id}>
          <MatchCard {...match} />
        </Link>
      ))}
    </div>
  );
};

export default MatchList;
