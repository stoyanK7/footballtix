import { faArrowDown, faArrowUp } from '@fortawesome/free-solid-svg-icons';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import Loading from '../shared/Loading';
import MatchList from '../shared/MatchList';
import MessageBox from '../shared/MessageBox';
import useFetch from '../../hooks/useFetch';
import { useState } from 'react';

const PastMatches = () => {
  const { fetchData: pastMatches, isFetching, fetchError } = useFetch('/api/matches/past');
  const [showMore, setShowMore] = useState(5);

  return (
    <>
      {isFetching && <Loading />}
      {fetchError && <MessageBox content={fetchError} type='error' />}
      {pastMatches &&
        <>
          <MatchList matches={pastMatches.slice(0, showMore)} />
          {showMore >= pastMatches.length && pastMatches.length > 5 &&
            <button
              className='show-more'
              onClick={() => setShowMore(5)}>
              Collapse <FontAwesomeIcon className='nav-icon fa-fw' icon={faArrowUp} />
            </button>
          }
          {showMore < pastMatches.length &&
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

export default PastMatches;
