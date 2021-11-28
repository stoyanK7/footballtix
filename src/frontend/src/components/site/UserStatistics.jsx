import '../../css/site/UserStatistics.css';

import { ResponsiveBar } from '@nivo/bar'

const UserStatistics = () => {
  return (
    <div className='user-statistics'>
      <div style={{ position: 'relative', height: 200 }}>
        <div style={{ position: 'absolute', width: '100%', height: '100%' }}>
          <ResponsiveBar
            data={[
              {
                "country": "AD",
                "hot dog": 198,
                "hot dogColor": "hsl(329, 70%, 50%)",
                "burger": 2,
                "burgerColor": "hsl(114, 70%, 50%)",
                "sandwich": 32,
                "sandwichColor": "hsl(220, 70%, 50%)",
                "kebab": 12,
                "kebabColor": "hsl(287, 70%, 50%)",
                "fries": 137,
                "friesColor": "hsl(315, 70%, 50%)",
                "donut": 157,
                "donutColor": "hsl(133, 70%, 50%)"
              },
              {
                "country": "AE",
                "hot dog": 18,
                "hot dogColor": "hsl(338, 70%, 50%)",
                "burger": 75,
                "burgerColor": "hsl(103, 70%, 50%)",
                "sandwich": 136,
                "sandwichColor": "hsl(74, 70%, 50%)",
                "kebab": 125,
                "kebabColor": "hsl(240, 70%, 50%)",
                "fries": 135,
                "friesColor": "hsl(246, 70%, 50%)",
                "donut": 132,
                "donutColor": "hsl(155, 70%, 50%)"
              },
              {
                "country": "AF",
                "hot dog": 163,
                "hot dogColor": "hsl(249, 70%, 50%)",
                "burger": 162,
                "burgerColor": "hsl(355, 70%, 50%)",
                "sandwich": 62,
                "sandwichColor": "hsl(221, 70%, 50%)",
                "kebab": 19,
                "kebabColor": "hsl(308, 70%, 50%)",
                "fries": 39,
                "friesColor": "hsl(173, 70%, 50%)",
                "donut": 19,
                "donutColor": "hsl(227, 70%, 50%)"
              },
              {
                "country": "AG",
                "hot dog": 170,
                "hot dogColor": "hsl(91, 70%, 50%)",
                "burger": 114,
                "burgerColor": "hsl(321, 70%, 50%)",
                "sandwich": 194,
                "sandwichColor": "hsl(353, 70%, 50%)",
                "kebab": 10,
                "kebabColor": "hsl(199, 70%, 50%)",
                "fries": 125,
                "friesColor": "hsl(58, 70%, 50%)",
                "donut": 111,
                "donutColor": "hsl(2, 70%, 50%)"
              },
              {
                "country": "AI",
                "hot dog": 56,
                "hot dogColor": "hsl(33, 70%, 50%)",
                "burger": 172,
                "burgerColor": "hsl(181, 70%, 50%)",
                "sandwich": 120,
                "sandwichColor": "hsl(325, 70%, 50%)",
                "kebab": 158,
                "kebabColor": "hsl(187, 70%, 50%)",
                "fries": 36,
                "friesColor": "hsl(58, 70%, 50%)",
                "donut": 147,
                "donutColor": "hsl(72, 70%, 50%)"
              },
              {
                "country": "AL",
                "hot dog": 47,
                "hot dogColor": "hsl(349, 70%, 50%)",
                "burger": 187,
                "burgerColor": "hsl(209, 70%, 50%)",
                "sandwich": 102,
                "sandwichColor": "hsl(263, 70%, 50%)",
                "kebab": 169,
                "kebabColor": "hsl(219, 70%, 50%)",
                "fries": 17,
                "friesColor": "hsl(186, 70%, 50%)",
                "donut": 159,
                "donutColor": "hsl(252, 70%, 50%)"
              },
              {
                "country": "AM",
                "hot dog": 50,
                "hot dogColor": "hsl(138, 70%, 50%)",
                "burger": 195,
                "burgerColor": "hsl(277, 70%, 50%)",
                "sandwich": 89,
                "sandwichColor": "hsl(220, 70%, 50%)",
                "kebab": 21,
                "kebabColor": "hsl(352, 70%, 50%)",
                "fries": 35,
                "friesColor": "hsl(45, 70%, 50%)",
                "donut": 92,
                "donutColor": "hsl(52, 70%, 50%)"
              }
            ]}
            keys={['hot dog', 'burger', 'sandwich', 'kebab', 'fries', 'donut']}
            indexBy="country"
            margin={{ top: 50, right: 130, bottom: 50, left: 60 }}
            padding={0.3}
            valueScale={{ type: 'linear' }}
            indexScale={{ type: 'band', round: true }}
            colors={{ scheme: 'nivo' }}
            defs={[
              {
                id: 'dots',
                type: 'patternDots',
                background: 'inherit',
                color: '#38bcb2',
                size: 4,
                padding: 1,
                stagger: true
              },
              {
                id: 'lines',
                type: 'patternLines',
                background: 'inherit',
                color: '#eed312',
                rotation: -45,
                lineWidth: 6,
                spacing: 10
              }
            ]}
            fill={[
              {
                match: {
                  id: 'fries'
                },
                id: 'dots'
              },
              {
                match: {
                  id: 'sandwich'
                },
                id: 'lines'
              }
            ]}
            borderColor={{ from: 'color', modifiers: [['darker', 1.6]] }}
            axisTop={null}
            axisRight={null}
            axisBottom={{
              tickSize: 5,
              tickPadding: 5,
              tickRotation: 0,
              legend: 'country',
              legendPosition: 'middle',
              legendOffset: 32
            }}
            axisLeft={{
              tickSize: 5,
              tickPadding: 5,
              tickRotation: 0,
              legend: 'food',
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
                translateX: 120,
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
            role="application"
            ariaLabel="Nivo bar chart demo"
            barAriaLabel={function (e) { return e.id + ": " + e.formattedValue + " in country: " + e.indexValue }} />
        </div>
      </div>

    </div>
  );
}

export default UserStatistics;
