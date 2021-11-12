import { useEffect, useRef, useState } from 'react';

import MessageBox from '../shared/MessageBox';

const PayPal = ({ onSuccess, onError, amount, description }) => {
  const [paid, setPaid] = useState(false);
  const [error, setError] = useState(null);
  const paypalRef = useRef();

  useEffect(() => {
    window.paypal
      .Buttons({
        createOrder: (data, actions) => {
          return actions.order.create({
            intent: 'CAPTURE',
            purchase_units: [
              {
                description: description,
                amount: {
                  currency_code: 'EUR',
                  value: amount,
                },
              },
            ],
          });
        },
        onApprove: async (data, actions) => {
          const order = await actions.order.capture();
          setPaid(true);
          onSuccess(order);
        },
        onError: (err) => {
          setError(err);
          onError();
        },
      })
      .render(paypalRef.current);
  }, []);

  if (paid) return <h2>Payment successful.</h2>;

  return (
    <>
      {error &&
        <MessageBox content={`Error occurred in processing payment: ${error}`} type='error' />
      }
      <div>
        <h4>Total Amount in EUR : €{amount}</h4>
        <div ref={paypalRef} />
      </div>
    </>
  );
};

export default PayPal;
