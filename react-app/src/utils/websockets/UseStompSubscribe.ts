import React, { useEffect } from "react";
import { Message } from "@stomp/stompjs";
import { useWebsocketContext } from "./WebSocketProvider";

export type StompOnReceivedCallback = (message: string) => void;

const UseStompSubscribe = (topic: string, onRecieved: StompOnReceivedCallback): void => {
  const wsContext = useWebsocketContext();

  useEffect(() => {
    if (wsContext !== null && wsContext.isWebSocketConnected) {
      wsContext.stompClient?.subscribe(topic, (message: Message) => onRecieved(message.body));
    }

    return () => {
      wsContext?.stompClient?.unsubscribe(topic);
    };
  }, [wsContext, topic, onRecieved]);
};

export default UseStompSubscribe;
