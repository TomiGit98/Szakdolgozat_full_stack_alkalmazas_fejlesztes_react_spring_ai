import UnauthenticatedNavbar from './UnauthenticatedPageComponents/UnauthenticatedNavbar';
import UnauthenticatedMainContent from './UnauthenticatedPageComponents/UnauthenticatedMainContent.js';
import UnauthenticatedFooter from './UnauthenticatedPageComponents/UnauthenticatedFooter';

function UnauthenticatedPage() {
    return (
        <div>
            <UnauthenticatedNavbar/>
            <UnauthenticatedMainContent />
            <UnauthenticatedFooter/>
        </div>
    );
}

export default UnauthenticatedPage;