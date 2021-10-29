import { useEffect, useState } from 'react';

import { Link } from 'react-router-dom';
import MatchCard from './MatchCard';
import axios from 'axios';

const MatchList = () => {
  const [matches, setMatches] = useState([]);

  useEffect(() => {
    axios.get('/api/matches/upcoming')
      .then(res => setMatches(res.data))
  }, []);

  return (
    <div>
      {matches.map(match => (
        <Link to={'/matches/' + match.id}>
          <MatchCard {...match} key={match.id} />
        </Link>
      ))}
    </div>
  );
};

export default MatchList;
