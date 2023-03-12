import React from "react";
import useStompWebSocket, { UseStompWebSocketState } from "./UseStompWebsocket";

const WebsocketContext = React.createContext<UseStompWebSocketState | null>(null);

export const useWebsocketContext = (): UseStompWebSocketState | null => {
  const context = React.useContext(WebsocketContext);
  if (context === undefined) {
    throw new Error("useWebsocketContext must be used within a WebSocketProvider");
  }
  return context;
};

const WebSocketProvider: React.FC = ({ children }) => {
  const state = useStompWebSocket({ socketPath: "/stomp" });

  return <WebsocketContext.Provider value={state}>{children}</WebsocketContext.Provider>;
};

export default WebSocketProvider;
