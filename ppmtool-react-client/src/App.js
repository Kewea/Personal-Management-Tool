import React from 'react';
import logo from './logo.svg';
import './App.css';
import Dashboard from './component/Dashboard';
import Header from './component/Layout/Header';
import 'bootstrap/dist/css/bootstrap.min.css'

function App() {
  return (
    <div className="App">
    <Header />
      {/* <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header> */}
      <Dashboard />
    </div>
  );
}

export default App;
