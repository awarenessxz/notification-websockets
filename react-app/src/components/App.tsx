import React from "react";
import { ToastContainer, toast } from "react-toastify";
import useStompSubscribe from "../utils/websockets/UseStompSubscribe";
import useStompPubish from "../utils/websockets/UseStompPublish";
import logo from "../logo.svg";
import "./App.css";
import "react-toastify/dist/ReactToastify.css";

function App() {
  const onRecieved = (message: string) => {
    toast(message);
  };

  useStompSubscribe("/topic/toast_message", onRecieved);
  const publishMessage = useStompPubish("/server/test");

  publishMessage("Testing 123!");

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.tsx</code> and save to reload.
        </p>
        <a className="App-link" href="https://reactjs.org" target="_blank" rel="noopener noreferrer">
          Learn React
        </a>
      </header>
      <ToastContainer />
    </div>
  );
}

export default App;
