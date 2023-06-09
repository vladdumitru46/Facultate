import {AddArtist, DeleteArtist, GetArtists, UpdateArtist} from "./utils/rest-calls.js";
import {useEffect, useState} from "react";
import ArtistForm from "./ArtistForm.jsx";
import ArtistTable from "./ArtistTable.jsx";

export default function ArtistApp() {
    const [artists, setArtists] = useState([{"stageName": "kk", "name": "fdgaa", "age": 0}]);

    function addFunction(artist) {
        AddArtist(artist)
            .then(res => GetArtists())
            .then(artists => setArtists(artists))
            .catch(error => console.log("eroare add ", error));
    }

    function deleteFunction(artist) {
        DeleteArtist(artist)
            .then(res => GetArtists())
            .then(artists => setArtists(artists))
            .catch(error => console.log('eroare delete', error));
    }

    function updateFunction(artist){
        UpdateArtist(artist)
            .then(res => GetArtists())
            .then(artists => setArtists(artists))
            .catch(error => console.log('eroare update', error));
    }

    useEffect(()=>{
        console.log('inside useEffect')
        GetArtists().then(arti=>setArtists(arti));},[]);


    return (<div className="ArtistApp">
        <h1>New Artist management app</h1>
        <ArtistForm addFunction={addFunction} updateFunction={ updateFunction}/>
        <br/>
        <br/>
        <ArtistTable artists={artists} deleteFunction={deleteFunction}/>
    </div>);
}