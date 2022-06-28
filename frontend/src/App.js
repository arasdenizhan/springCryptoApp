import './App.css';
import React, {useEffect, useState} from "react";
import {AppBar, Box, Card, CardContent, CssBaseline, Grid, ThemeProvider, Toolbar, Typography} from "@mui/material";
import {DataGrid} from '@mui/x-data-grid';
import {AppTheme} from "./AppTheme";
import axios from "axios";
import SpaceBackground from "./bgspace.jpg";

const columns = [
    { field: 'rank', headerName: 'Rank'},
    { field: 'symbol', headerName: 'Symbol'},
    { field: 'name', headerName: 'Name', width: 150},
    { field: 'price', headerName: 'Price (USD)', type: 'number'}
];


function App() {
  return (
      <ThemeProvider theme={AppTheme}>
          <div style={{ backgroundImage:`url(${SpaceBackground})` }}>
              <CssBaseline/>
              <HeaderBar/>
              <PricesCard/>
          </div>
      </ThemeProvider>
  );
}

function HeaderBar() {
    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static" color={'third'}>
                <Toolbar>
                    <img width={32} height={32} src={require('./galaxy.png')}  alt={"Galactic Gold"}/>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 , textAlign: "center", color: "#000000"}} >
                        Galactic Gold - Prices
                    </Typography>
                </Toolbar>
            </AppBar>
        </Box>
        )
}

function PricesCard() {
    return (
        <Grid   container
                spacing={0}
                padding={5}
                direction="column"
                alignItems="center"
                style={{ minHeight: '100vh', minWidth: '100vh' }}>
            <Card sx={{ minWidth: 500, alignSelf: "center"}}>
                <CardContent>
                    <GetPriceTable/>
                </CardContent>
            </Card>
        </Grid>
    );
}

function GetPriceTable() {
    const [rows, setRows] = useState([]);
    useEffect(() => {
        getCurrencyAssets().then(resp => setRows(resp))
        setInterval(() => {
            getCurrencyAssets().then(resp => setRows(resp))
            console.log("deneme")
        }, 10000);
    }, []);
    return (
        <div style={{ height: 650, width: 600 }}>
            <DataGrid
                rows={rows}
                columns={columns}
                pageSize={10}
                rowsPerPageOptions={[10]}
            />
        </div>
    );
}


async function getCurrencyAssets() {
    let result = []
    await axios.get('http://localhost:8080/api/v1/crypto/list')
        .then(function (response) {
            result = response.data
        })
        .catch(function (error) {
            alert(error);
        })
        .then(function () {
            //do nothing
        });
    return result
}

export default App;
