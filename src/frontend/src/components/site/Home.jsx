import { faArrowDown, faArrowUp } from '@fortawesome/free-solid-svg-icons';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import Loading from '../shared/Loading';
import MatchList from '../shared/MatchList';
import MessageBox from '../shared/MessageBox';
import useFetch from '../../hooks/useFetch';
import { useState } from 'react';

const Home = () => {
  const { fetchData: upcomingMatches, isFetching, fetchError } = useFetch('/api/matches/upcoming');
  const [showMore, setShowMore] = useState(5);

  return (
    <>
      {isFetching && <Loading />}
      {fetchError && <MessageBox content={fetchError} type='error' />}
      {upcomingMatches &&
        <>
          <MatchList matches={upcomingMatches.slice(0, showMore)} />
          {showMore >= upcomingMatches.length && upcomingMatches.length > 5 &&
            <button
              className='show-more'
              onClick={() => setShowMore(5)}>
              Collapse <FontAwesomeIcon className='nav-icon fa-fw' icon={faArrowUp} />
            </button>
          }
          {showMore < upcomingMatches.length &&
            <button
              className='show-more'
              onClick={() => setShowMore(showMore + 5)}>
              Show more <FontAwesomeIcon className='nav-icon fa-fw' icon={faArrowDown} />
            </button>
          }
        </>
      }
    </>
  );
};

export default Home;
