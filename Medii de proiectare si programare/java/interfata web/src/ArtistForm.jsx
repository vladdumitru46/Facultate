import {useState} from "react";

export default function ArtistForm({ addFunction, updateFunction }) {
    const [stageName, setStageName] = useState("");
    const [name, setName] = useState("");
    const [age, setAge] = useState(0);

    function handleSubmit(event) {
        const artist = {
            stageName: stageName,
            name: name,
            age: age };
        addFunction(artist);
        event.preventDefault();
    }

    function handleUpdate(event){
        const artist = {
            stageName: stageName,
            name: name,
            age: age };
        updateFunction(artist);
        event.preventDefault();
    }

    return (
        <form onSubmit={handleSubmit}>
            <label>
                StageName: <input type="text" value={stageName} onChange={e => setStageName((e.target.value))}/>
            </label><br/>
            <label>
                Name: <input type="text" value={name} onChange={e => setName((e.target.value))}/>
            </label><br/>
            <label>
                Age: <input type="number" value={age} onChange={e => setAge(parseInt(e.target.value))}/>
            </label><br/>
            <input type={"submit"} value="Add"/>
            <button onClick={handleUpdate} >Update</button>
        </form>
    )
}

