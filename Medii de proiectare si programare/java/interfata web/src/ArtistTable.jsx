function ArtistRow({artist, deleteFunction}){
    function handleDelete(event){
        deleteFunction(artist.stageName);
    }
    return(
        <tr>
            <td>{artist.stageName}</td>
            <td>{artist.name}</td>
            <td>{artist.age}</td>
            <td><button onClick={handleDelete}>Delete</button> </td>
        </tr>
    )
}
export default function ArtistTable({artists, deleteFunction}){
    let rows = [];
    artists.forEach(function (artist) {
        rows.push(<ArtistRow artist={artist} key={artist.stageName} deleteFunction={deleteFunction} />);
    });
    return(
        <div className="ArtistTable">
            <table className="center" border="3px solid-white" width="400px">
                <thead>
                <tr align="center">
                    <th>StageName</th>
                    <th>Name</th>
                    <th>Age</th>
                </tr>
                </thead>
                <tbody align="center">{rows}</tbody>
            </table>
        </div>
    );
}