import '../../css/shared/Chart.css';

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { ResponsiveBar } from '@nivo/bar';
import { faTimesCircle } from "@fortawesome/free-solid-svg-icons";
import { forwardRef } from "react";

const Chart = forwardRef(({ data, fullScreen, disableFullScreen }, ref) => {


  return (
    <div ref={ref}
      className={`${fullScreen ? 'chart-full-screen' : ''} chart-wrapper-1`}>
      <div className='chart-wrapper-2' >
        {fullScreen && <FontAwesomeIcon icon={faTimesCircle} className='fa-circle' onClick={disableFullScreen} />}
        <div className='chart'>
          <ResponsiveBar
            data={data}
            keys={['registeredUsers']}
            indexBy='date'
            margin={{ top: 50, right: 130, bottom: 50, left: 60 }}
            padding={0.3}
            valueScale={{ type: 'linear' }}
            indexScale={{ type: 'band', round: true }}
            colors={{ scheme: 'accent' }}
            borderColor={{ from: 'color', modifiers: [['darker', 1.6]] }}
            axisTop={null}
            axisRight={null}
            axisBottom={{
              tickSize: 5,
              tickPadding: 5,
              tickRotation: 0,
              legend: 'Date',
              legendPosition: 'middle',
              legendOffset: 32
            }}
            axisLeft={{
              tickSize: 5,
              tickPadding: 5,
              tickRotation: 0,
              legend: 'Registered users',
              legendPosition: 'middle',
              legendOffset: -40
            }}
            labelSkipWidth={12}
            labelSkipHeight={12}
            labelTextColor={{ from: 'color', modifiers: [['darker', 1.6]] }}
            legends={[
              {
                dataFrom: 'keys',
                anchor: 'bottom-right',
                direction: 'column',
                justify: false,
                translateX: 100,
                translateY: 0,
                itemsSpacing: 2,
                itemWidth: 100,
                itemHeight: 20,
                itemDirection: 'left-to-right',
                itemOpacity: 0.85,
                symbolSize: 20,
                effects: [
                  {
                    on: 'hover',
                    style: {
                      itemOpacity: 1
                    }
                  }
                ]
              }
            ]}
            role='application'
            barAriaLabel={function (e) { return e.id + ': ' + e.formattedValue + ' in country: ' + e.indexValue }} />
        </div>
      </div>
    </div>
  );
});

export default Chart;
