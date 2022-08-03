import React from "react";
import { useWebsocketContext } from "./WebSocketProvider";

export type StompPublishCallback = (message: string) => void;

const UseStompPubish = (topic: string): StompPublishCallback => {
  const wsContext = useWebsocketContext();

  const publishMessage = (message: string) => {
    if (wsContext !== null && wsContext.isWebSocketConnected) {
      console.log("PUBLISHING MESSAGE " + message);
      wsContext.stompClient?.publish({ destination: topic, body: message });
    }
  };

  return publishMessage;
};

export default UseStompPubish;
