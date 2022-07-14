import { BrowserRouter, Routes, Route } from "react-router-dom";
import {useState} from "react"

import Main from './components/Main/Main';
import LoginScreen from "./components/LoginScreen/LoginScreen"
import RegisterScreen from "./components/RegisterScreen/RegisterScreen"
import FilmProfile from "./components/FilmProfile/FilmProfile"
import AddMovie from "./components/AddMovie/AddMovie";


function App() {

  const [didUserLogin,setDidUserLogin]=useState(false);
  
  return (

  <BrowserRouter>
    <Routes>
      <Route path="/" element={<Main setDidUserLogin={setDidUserLogin} didUserLogin={didUserLogin} />} />
      <Route path="Main" element={<Main setDidUserLogin={setDidUserLogin} didUserLogin={didUserLogin} />} />
      <Route path="LoginScreen" element={<LoginScreen setDidUserLogin={setDidUserLogin}  />} />
      <Route path="RegisterScreen" element={<RegisterScreen  />} />
      <Route path="FilmProfile" element={<FilmProfile setDidUserLogin={setDidUserLogin} didUserLogin={didUserLogin} />} />
      <Route path="AddMovie" element={<AddMovie setDidUserLogin={setDidUserLogin} didUserLogin={didUserLogin}  />} />
    </Routes>
  </BrowserRouter>


  );
}

export default App;
