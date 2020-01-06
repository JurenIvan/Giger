import { notification } from 'antd';

export function notify(type, message, description, duration = 2) {
  return notification[type]({
    message,
    description,
    duration,
    placement: 'bottomRight',
    style: {
      whiteSpace: 'pre-line',
    },
  });
}
