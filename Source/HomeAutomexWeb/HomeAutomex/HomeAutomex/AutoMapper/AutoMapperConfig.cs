using AutoMapper;
using HomeAutomex.AutoMapper.Mappers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HomeAutomex.AutoMapper
{
    public class AutoMapperConfig
    {
        public static void RegisterMappings()
        {
            Mapper.AddProfile<UsuarioAutoMapper>();
            Mapper.AddProfile<ResidenciaAutoMapper>();
            Mapper.AddProfile<ModuloAutoMapper>();
            Mapper.AddProfile<AmbienteAutoMapper>();
            Mapper.AddProfile<DispositivoAutoMapper>();
            Mapper.AddProfile<PortaAutoMapper>();
        }
    }
}