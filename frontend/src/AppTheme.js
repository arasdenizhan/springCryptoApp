import { createTheme } from '@mui/material/styles';

export const AppTheme = createTheme({
    palette: {
        type: 'light',
        primary: {
            main: '#5800FF',
            contrastText: '#fafafa',
        },
        secondary: {
            main: '#E900FF',
        },
        third: {
            main: '#FFC600',
        },
        background: {
            default: '#000000',
        },
        text: {
            primary: '#000000',
        },
    },
});